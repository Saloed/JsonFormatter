docker run -p 8080:8080 -p 50000:50000 \
-v /var/run/docker.sock:/var/run/docker.sock \
-v $PWD/jenkins_data:/var/jenkins_home \
--rm -u root h1kkan/jenkins-docker:lts
