cat <<EOL > Jenkinsfile
pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }
    stage('Archive Artifacts') {
      steps {
        archiveArtifacts artifacts: '**/*', excludes: '**/.git/**'
      }
    }
  }

  post {
    success {
      echo "Pipeline succeeded"
    }
    failure {
      echo "Pipeline failed — check console logs"
    }
  }
}
EOL
