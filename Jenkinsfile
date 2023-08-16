import java.text.SimpleDateFormat
import java.util.Date

def dateFormat = new SimpleDateFormat("yyyyMMddHHmm")
def date = new Date()
def timestamp = dateFormat.format(date).toString()
def correo = "jhonmydanc@gmail.com"

pipeline {
    agent any
    stages {
        stage('Run Test') {
            steps {
                script {
                    // Utiliza la ruta completa de Gradle aquí, y utiliza "bat" para ejecutar comandos de Windows
                    bat 'C:\\Gradle\\gradle-8.2.1\\bin\\gradle.bat clean test aggregate --warning-mode all'
                    echo 'Test Ejecutados Exitosamente'
                }
            }
        }
        stage('Generate Reports') {
            steps {
                script {
                    bat "rename \"${WORKSPACE}\\target\" serenity_${timestamp}"
                    echo 'Backup de evidencias realizado con exito'
                }
            }
            post {
                always {
                    // Utilizar el plugin HTML Publisher para publicar los informes HTML
                    publishHTML([
                        allowMissing: false,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: "${WORKSPACE}\\serenity_${timestamp}",
                        reportFiles: 'target/index.html',
                        reportName: 'Reporte Modulo de autogestion',
                        reportTitles: 'Modulo de Autogestion'
                    ])
                    echo 'Reporte Html realizado con exito'
                }
            }
        }
    }
}







