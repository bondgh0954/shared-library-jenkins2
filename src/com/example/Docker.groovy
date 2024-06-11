#!/user/bin/env groovy

package com.example
class Docker implements Serializable{
    def script
    Docker(script){
        this.script = Script
    }



    buildJar(){
        script.echo 'building application artifact'
        script.sh 'mvn package'


    }
    dockerLogin(){
        script.echo 'logging in to dockerhub repository'
        script.withCredentials([script.usernamePassword(credentialsId:'dockerhub-credentials',usernameVariable:'USER',passwordVariable:'PASS')]){
            script.sh "echo '${PASS}' | docker login -u '${USER}' --password-stdin"
        }

    }
    buildImage(String imageName){
        script.echo 'building image from the application artifact'
        script.sh "docker build -t $imageName"
    }

    pushImage(String imageName){
        script.echo 'pushing image to the docker repository'
        script.sh "docker push $imageName"
    }
}