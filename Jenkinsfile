pipeline {
	agent none
	options {
		buildDiscarder(logRotator(numToKeepStr: '2', artifactNumToKeepStr: '1'))
	}
	stages {
		stage('Unit test') {
			agent {
       			         label 'Linux:'
       			}
			steps {
				sh 'ant -f test.xml -v'
				junit 'reports/Result.xml'
			}
		}
		stage('build') {
			agent {
				label 'Master'
			}
			steps {
				sh 'ant -f build.xml -v'
			}
			post {
                		success {
                        		archiveArtifacts artifacts: 'dist/*.jar', fingerprint: true
               			 }
			}
		}
		stage('deploy') {
			agent {
				label 'Master'
			}
			steps {
				sh 'cp dist/rectangle_${BUILD_NUMBER}.jar /var/www/html/rectangles/all/'
			}
		}
		stage('Running on Centos') {
			agent {
				label 'Linux:'
			}
			steps {
				sh 'wget http://jerry-john3.mylabserver.com/rectangles/all/rectangle_${BUILD_NUMBER}.jar'
				sh 'java -jar rectangle_${BUILD_NUMBER}.jar 7 8'
			}
		}
		stage('Running it in Docker Container') {
			agent {
				docker 'openjdk:8u171-jre'
			}
			steps {
				sh 'wget http://jerry-john3.mylabserver.com/rectangles/all/rectangle_${BUILD_NUMBER}.jar'
				sh 'java -jar rectangle_${BUILD_NUMBER}.jar 8 4'
			}
		}
	}	
}
