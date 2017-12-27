node {
   stage('Preparation') {
      git 'https://github.com/Saloed/JsonFormatter'
   }
   stage('Build') {
      sh  './gradlew docker'
   }
}
