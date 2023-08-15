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
                    bat "rename \"${WORKSPACE}\\target\" serenity_${timestamp}"
                    echo 'Backup de evidencias realizado con exito'

                    publishHTML([
                        allowMissing: false,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: "${WORKSPACE}\\serenity_${timestamp}",
                        reportFiles: 'index.html',
                        reportName: 'Reporte Modulo de autogestión',
                        reportTitles: 'Modulo de Autogestión'
                    ])
                    echo 'Reporte Html realizado con exito'
                }
            }
        }
    }
}




