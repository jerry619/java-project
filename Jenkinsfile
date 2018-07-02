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
				sh "if [ ! -d '/var/www/html/rectangles/all/${BRANCH_NAME}' ]; then mkdir /var/www/html/rectangles/all/${BRANCH_NAME}; fi"
				sh 'cp dist/rectangle_${BUILD_NUMBER}.jar  /var/www/html/rectangles/all/${BRANCH_NAME}/'
			}
		}
		stage('Running on Centos') {
			agent {
				label 'Linux:'
			}
			steps {
				sh 'wget http://jerry-john3.mylabserver.com/rectangles/all/${BRANCH_NAME}/rectangle_${BUILD_NUMBER}.jar'
				sh 'java -jar rectangle_${BUILD_NUMBER}.jar 7 8'
			}
		}
		stage('Running it in Docker Container') {
			agent {
				docker {
					image 'openjdk:8u171-jre'
					label 'Linux:'
				}
			}
			steps {
				sh 'echo ${NODE_NAME}'
				sh 'wget http://jerry-john3.mylabserver.com/rectangles/all/${BRANCH_NAME}/rectangle_${BUILD_NUMBER}.jar'
				sh 'java -jar rectangle_${BUILD_NUMBER}.jar 8 4'
			}
		}
		stage('Merge the code to Master from development') {
			agent {
				label 'Master'
			}
			steps {
				sh 'echo stashing any local changes'
				sh 'git stash'
				sh 'git checkout development'
				sh 'git checkout master'
				sh 'git merge development'
				sh (script: "git describe --abbrev=0 --tags | cut -d'-' -f2" > rel.txt)
				sh 'cat $rel.txt'
				sh 'git push origin master'
			}
			post {
                        	success {
                                	sh "cp /var/www/html/rectangles/all/${BRANCH_NAME}/rectangle_${BUILD_NUMBER}.jar /var/www/html/rectangles/green/"
				}
                       }
                }
	}			
}
