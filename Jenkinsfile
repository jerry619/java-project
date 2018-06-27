pipeline {
	agent any
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
	}
	post {
		success {
			archiveArtifacts artifacts: 'dist/*.jar', fingerprint: true
		}
	}
}
