pipeline {
	agent any
	options {
		buildDiscarder(logRotator(numToKeepStr: '2', artifactnumToKeepStr: '1'))
	}
	stages {
		stage('build') {
			steps {
				sh 'ant -f build.xml'
			}
		}
	}
	post {
		always {
			archiveArtifacts artifacts: 'dist/*.jar', fingerprint: true
		}
	}
}
