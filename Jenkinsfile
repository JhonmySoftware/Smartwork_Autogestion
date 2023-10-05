def dateFormat = new SimpleDateFormat("yyyyMMddHHmm")
def date = new Date()
def timestamp = dateFormat.format(date).toString()

pipeline {
    agent any
    parameters {
        string(name: 'CORREO', defaultValue: 'jhon.quinones@infotrack.com.co', description: 'Correo para notificaciones')
    }
    stages {
        stage('Run Test') {
            steps {
                script {
                    runTests()
                }
            }
        }

        stage('Generate Reports') {
            steps {
                script {
                    generateReports()
                }
            }
        }
    }
    post {
        always {
            // Pasos comunes a ejecutar siempre
        }
        failure {
            // Enviar correo electrónico en caso de fallo
            mail to: "${params.CORREO}",
                 subject: "Fallo en la Build: ${currentBuild.fullDisplayName}",
                 body: "El build ha fallado. Verifica los logs en ${env.BUILD_URL}"
        }
    }
}

def runTests() {
    bat 'gradle clean test aggregate --warning-mode none' // cambiar conforme a tus necesidades
    echo 'Tests ejecutados exitosamente'
}

def generateReports() {
    bat "move /Y \"${WORKSPACE}\\target\" \"${WORKSPACE}\\serenity_${timestamp}\""
    echo 'Backup de evidencias realizado con éxito'
    publishHTML([
        allowMissing: false,
        alwaysLinkToLastBuild: true,
        keepAll: true,
        reportDir: "serenity_${timestamp}",
        reportFiles: 'index.html',
        reportName: 'Reporte Módulo de autogestión',
        reportTitles: 'Módulo de Autogestión'
    ])
    echo 'Reporte HTML generado con éxito'
}










