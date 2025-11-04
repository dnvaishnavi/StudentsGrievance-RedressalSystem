let complaints = [];

// Fetch complaints from backend (showing last 5)
async function loadComplaints() {
  try {
    const res = await fetch("http://localhost:9090/api/grievances/all");
    const data = await res.json();
    complaints = data.reverse().slice(0, 5); // recent 5 only
    displayComplaints(complaints);
    updateCounts();
  } catch (err) {
    console.error("Error loading complaints:", err);
  }
}

// Display complaints in table
function displayComplaints(list) {
  const tbody = document.getElementById("complaintTable");
  tbody.innerHTML = "";
  list.forEach(c => {
    const tr = document.createElement("tr");
    tr.innerHTML = `
      <td>${c.id}</td>
      <td>${c.subject}</td>
      <td><span class="badge ${getStatusBadge(c.status)}">${c.status}</span></td>
      <td>${c.actionTaken || "-"}</td>
      <td><button class="btn btn-sm btn-outline-primary" onclick="openEditModal(${c.id})">Edit</button></td>
    `;
    tbody.appendChild(tr);
  });
}

function getStatusBadge(status) {
  if (status === "Resolved") return "bg-success";
  if (status === "Pending") return "bg-warning text-dark";
  if (status === "In Progress") return "bg-info text-dark";
  return "bg-secondary";
}

// Search functionality
function searchComplaints() {
  const term = document.getElementById("searchInput").value.toLowerCase();
  const filtered = complaints.filter(c =>
    c.subject.toLowerCase().includes(term) ||
    c.status.toLowerCase().includes(term) ||
    c.id.toString().includes(term)
  );
  displayComplaints(filtered);
}

// Count cards
function updateCounts() {
  const resolved = complaints.filter(c => c.status === "Resolved").length;
  const pending = complaints.filter(c => c.status === "Pending" || c.status === "In Progress").length;
  document.getElementById("resolved").innerText = resolved;
  document.getElementById("Pending").innerText = pending;
}

// Open modal to edit
function openEditModal(id) {
  const c = complaints.find(x => x.id === id);
  if (!c) return;
  document.getElementById("editId").value = c.id;
  document.getElementById("editStatus").value = c.status;
    document.getElementById("editDescription").value = c.description || "No description available";
  document.getElementById("editSuggestions").value = c.suggestions || "";
  document.getElementById("editAction").value = c.actionTaken || "";
  new bootstrap.Modal(document.getElementById("editModal")).show();
}

// Save edits (update DB)
async function saveEdit() {
  const id = document.getElementById("editId").value;
  const updated = {
    status: document.getElementById("editStatus").value,
    actionTaken: document.getElementById("editAction").value
  };

  try {
    const res = await fetch(`http://localhost:9090/api/grievances/user/${id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(updated)
    });
    if (res.ok) {
      alert("Complaint updated successfully!");
      loadComplaints(); // refresh list
      bootstrap.Modal.getInstance(document.getElementById("editModal")).hide();
    } else {
      alert("Failed to update complaint.");
    }
  } catch (err) {
    console.error("Error updating complaint:", err);
  }
}

function logout() {
  alert("You have been logged out!");
  window.location.href = "../login.html";
}

// Load on page start
document.addEventListener("DOMContentLoaded", loadComplaints);
