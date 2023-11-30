import java.text.SimpleDateFormat
import java.util.Date

def dateFormat = new SimpleDateFormat("yyyyMMddHHmm")
def date = new Date()
def timestamp = dateFormat.format(date).toString()
def correo = "jhon.quinones@infotrack.com.co"

pipeline {
    agent any
    stages {
        stage('Ejecución de pruebas') {
            steps {
                script {
                    // Utiliza la ruta completa de Gradle aquí, y utiliza "bat" para ejecutar comandos de Windows
                    bat 'C:\\Gradle\\gradle-8.3\\bin\\gradle.bat clean test aggregate --warning-mode all'
                    echo 'Test Ejecutados Exitosamente'
                }
            }
        }
        stage('Generar el Reporte') {
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
                        reportFiles: 'index.html',
                        reportName: 'Reporte Modulo de autogestion',
                        reportTitles: 'Modulo de Autogestion'
                    ])
                    echo 'Reporte Html realizado con exito'
                }
            }
        }
    }
}









