## BCI_Sergio_Ponce01

## Ejecutar API

- Para iniciar la APi msdataauth se debe ejecutar 
- Al iniciar la API msdataauth inicializa un banco de datos en memoria H2
  - Para tener acceso debe ir la url http://localhost:8080/api/msdataauth/h2-console/ 

    Credenciales:
    - url: jdbc:h2:mem:bcidb
    - username: sa
    - password: ""   

  - ![img.png](doc/h2_login.JPG)
-
- Para ejecutar cualquier servicio de la API:
- Primero debe generar el token "Authorization" (el token mientras esté vigente puede reutilizarse)
  - Ejemplo: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTcxMzkwNjk0MCwiaWF0IjoxNzEyMTA2OTQwfQ.eX-YfQtRpebxfKLYPBcJdR0VV0qm0AqfZfNXyL-FFsrj4m4CgKxZ_Xw4eOetw4d_34hUTXr3TM2xUjXKsBh5RQ 

- ![img.png](doc/api_login.JPG)

- Con el token generado, en el paso anterior, se puede consumir cualquier otro servicio de la API
- ![img.png](doc/api_user_save_token.JPG)
- ![img.png](doc/api_user_save_200.JPG)


---------------------------------------------------------------------------------------------

## -Diagrama Secuencias

- Api: POST http://localhost:8080/api/msdataauth/login
- ![img.png](doc/diagrama_login.JPG)
- 
- Api: POST http://localhost:8080/api/msdataauth/user/save
- ![img.png](doc/diagrama_save.JPG)
- 
- Api: GET http://localhost:8080/api/msdataauth/user/get/all
- ![img.png](doc/diagrama_get_all.JPG)
- 
- Api: GET http://localhost:8080/api/msdataauth/user/getbyemail/{email}
- ![img.png](doc/diagrama_get_email.JPG)
- 
- APi: DELETE http://localhost:8080/api/msdataauth/user/delete/juan1@rodriguez.org
- ![img.png](doc/diagrama_delete.JPG)
- 
- Api: PUT http://localhost:8080/api/msdataauth/user/update
- ![img.png](doc/diagrama_update.JPG)







