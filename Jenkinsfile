import java.text.SimpleDateFormat

def dateFormat = new SimpleDateFormat("yyyyMMddHHmm")
def date = new Date()
def timestamp = dateFormat.format(date).toString()
def correo = "jhonmydanc@gmail.com"

pipeline {
    agent any
    tools {
        // Específica la herramienta Gradle que configuraste en "Configuración de herramientas globales"
         gradle 'Gradle', 'C:\\Gradle\\gradle-8.0.2'
    }
    stages {
        stage('Run Test') {
            steps {
                script {
                    // Utiliza la ruta completa de Gradle aquí
                    bat '"C:\\Gradle\\gradle-8.0.2\\bin\\gradle.bat" clean test aggregate'
                    echo 'Test Ejecutados Exitosamente'
                }
            }
        }

          //Realizar Reporte de la Ejecucion
          stage('Generate Reports') {
              steps {
                 script {
                    bat " rename \"${WORKSPACE}\\target\" serenity_${timestamp}"
                    echo 'Backup de evidencias realizado con exito'

                    publishHTML([
                             allowMissing: false,
                             alwaysLinkToLastBuild: true,
                             keepAll: true,
                             reportDir: "${WORKSPACE}//serenity_${timestamp}",
                             reportFiles: 'index.html',
                             reportName: ' /*Nombre del Reporte*/ ',
                             reportTitles: ' /*Titulo del Reporte */ '
                          ])
                          echo 'Reporte Html realizado con exito'
                    }
                 }
              }

          //Realizar Analisis del Codigo con SonarQube
          stage('SonarQube Analysis') {
              steps {
                 script {
                    //Nombre del servidor con el que esta configurado en las Global Tools Jenkins
                    scannerHome = tool 'SonarQubeScanner'
                 }
                 //Nombre del servidor con el que esta configurado en la configuracion del sistema Jenkins
                 withSonarQubeEnv('SonarQube')
                       {
                          bat 'sonar-scanner'
                       }
                    }
                 }

       }
   }

