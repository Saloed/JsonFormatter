node {
   stage('Preparation') {
      git 'https://github.com/Saloed/JsonFormatter'
   }
   stage('Build') {
   	  sh 'docker build -f jenkins.Dockerfile .'
      sh  './gradlew docker'
   }
}
