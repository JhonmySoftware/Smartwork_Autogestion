import java.text.SimpleDateFormat
import java.util.Date

def dateFormat = new SimpleDateFormat("yyyyMMddHHmm")
def date = new Date()
def timestamp = dateFormat.format(date).toString()
def correo = "jhon.quinones@infotrack.com.co"

pipeline {
    agent any

    stages {
        stage('Run Test') {
            steps {
                script {
                    bat 'gradle clean test aggregate --warning-mode all'
                    echo 'Test Ejecutados Exitosamente'
                }
            }
        }

        stage('Generate Reports') {
            steps {
                script {
                    bat "move /Y \"${WORKSPACE}\\target\" \"${WORKSPACE}\\serenity_${timestamp}\""
                    echo 'Backup de evidencias realizado con exito'
                }
            }

            post {
                always {
                    // Publicar los informes HTML
                    publishHTML([
                        allowMissing: false,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: "serenity_${timestamp}",
                        reportFiles: 'index.html',
                        reportName: 'Reporte Modulo de autogestion',
                        reportTitles: 'Modulo de Autogestion'
                    ])
                    echo 'Reporte Html realizado con exito'
                }
            }
        }
    }

    post {
        always {
            // Aquí puedes añadir pasos que siempre se ejecutarán después de las etapas, por ejemplo, enviar notificaciones por correo.
        }
        failure {
            // Pasos que se ejecutarán solo cuando la build falle.
        }
    }
}








