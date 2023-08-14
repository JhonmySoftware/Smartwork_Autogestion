/* #################### IMPORTACION DE LIBRERIAS Y DECLARACION DE VARIABLES #################### */
import java.text.SimpleDateFormat

def dateFormat = new SimpleDateFormat("yyyyMMddHHmm")
def date = new Date()
def timestamp = dateFormat.format(date).toString()
def correo = "jhon.quinones@infotrack.com.co"
/* ####################################################################################### */

pipeline {
      agent any
      stages {

          //Ejecutar Automatizacion
          stage('Run Test') {
              steps {
                  script {
                     bat("gradle clean test aggregate")
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

