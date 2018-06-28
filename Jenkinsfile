pipeline {
	agent {
		label Master
	}
	options {
		buildDiscarder(logRotator(numToKeepStr: '2', artifactNumToKeepStr: '1'))
	}
	stages {
		stage('Unit test') {
			steps {
				sh 'ant -f test.xml -v'
				junit 'reports/Result.xml'
			}
		}
		stage('build') {
			steps {
				sh 'ant -f build.xml -v'
			}
		}
		stage('deploy') {
			steps {
				sh 'cp dist/rectangle_${env.BUILD_NUMBER}.jar /var/www/html/rectangles/all/'
			}
		}
	}
	post {
		success {
			archiveArtifacts artifacts: 'dist/*.jar', fingerprint: true
		}
	}
}
