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
				label 'Linux:'
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
	}
}
