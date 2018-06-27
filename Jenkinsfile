pipeline {
	agent any
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
