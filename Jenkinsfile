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
                    bat 'C:\\Gradle\\gradle-8.2.1\\bin\\gradle.bat clean test aggregate'
                    echo 'Test Ejecutados Exitosamente'
                }
            }
        }
        stage('Generate Reports') {
            steps {
                script {
                    def reportDir = "${WORKSPACE}/serenity_${timestamp}"

                    // Renombrar el directorio de informes
                    bat "rename \"${WORKSPACE}\\target\" ${reportDir}"
                    echo 'Backup de evidencias realizado con éxito'

                    // Publicar los informes HTML
                    publishHTML([
                        allowMissing: false,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: "${reportDir}",
                        reportFiles: 'index.html',
                        reportName: 'Reporte Modulo de Autogestion',
                        reportTitles: 'Modulo de Autogestion'
                    ])
                    echo 'Reporte HTML realizado con éxito'
                }
            }
        }
    }
}





