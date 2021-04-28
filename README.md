# Comandos para ejecutar el proyecto el local:

### 1. Tener instalado Docker y Maven

### 2. Limpiar y Compliar el proyecto
mvn clean install -DskipTests							

### 3. Ejecturar test unitarios y verificar la coberura de codigo (El codigo queda en mutantsDnaAPI\target\site\jacoco\index.html)

mvn clean jacoco:prepare-agent install jacoco:report

![N|Solid](https://raw.githubusercontent.com/santosvaldesm/MutantsDnaAPI_level3/master/assets/Captura.PNG)

### 4. Ejecutar docker-compose	

docker-compose up

### 5. estadisticas de haproxy (balanceador)
Usuario admin 
Clave   admin
URL      http://localhost:70/stats

![N|Solid](https://raw.githubusercontent.com/santosvaldesm/MutantsDnaAPI_level3/master/assets/Captura2.PNG)

##  6. Metodos expuestos por el microservicio 

Se adiciona el proyecto "MutantsApi-soapui-project.xml" para SOAPUI con los metodos de la API

### 6.1 POST /mutant/

Prueba por SOAP UI:

![N|Solid](https://raw.githubusercontent.com/santosvaldesm/MutantsDnaAPI_level3/master/assets/CapturaIsMutant.PNG)

Prueba por consola:

curl --header "Content-Type: application/json" --request POST --data '{"dna":["CTWCTG","CCGTGC","TGCAGT","TAAAAA","ATTTTT","TCATTC"]}' http://localhost/mutant/

### 6.2 GET /stats

Prueba por SOAP UI

![N|Solid](https://raw.githubusercontent.com/santosvaldesm/MutantsDnaAPI_level3/master/assets/CapturaStats.PNG)

Prueba por consola

curl http://localhost/stats

### 6.3 GET /address

![N|Solid](https://raw.githubusercontent.com/santosvaldesm/MutantsDnaAPI_level3/master/assets/CapturaAddress.PNG)

Prueba por consola

curl http://localhost/address
