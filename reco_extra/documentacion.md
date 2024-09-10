# API de Recomendación de Colaboradores para Beneficios Empresariales
## Descripción
Este servicio permite a las empresas obtener una lista de colaboradores recomendados para recibir beneficios, basados en ciertos criterios como la cantidad mínima de puntos obtenidos, la cantidad mínima de donaciones realizadas, y la cantidad máxima de colaboradores a recomendar.
Las empresas pueden integrar este servicio en sus propias plataformas para gestionar los beneficios de sus colaboradores de manera más efectiva.

## Endpoints
- `GET /recoextra`
### Descripción
Obtiene una lista de colaboradores que cumplen con los criterios especificados en los parámetros de la URL. Si no se especifica algún parámetro, el sistema lo asume como `0`.

### Parámetros
- minDonaciones (opcional): Cantidad mínima de donaciones de viandas realizadas en el último mes. Si no se especifica, se considera `0`.
- minPuntos (opcional): Cantidad mínima de puntos requeridos. Si no se especifica, se considera `0`.
- maxColaboradores (opcional): Cantidad máxima de colaboradores a devolver. Si no se especifica, se considera `10`.

### Ejemplo de URL
http://localhost:8080/recoextra?minDonaciones=0&minPuntos=20&maxColaboradores=3

En este ejemplo, se buscan colaboradores con un mínimo de 20 puntos, sin importar la cantidad de donaciones, y con un máximo de 3 colaboradores.

### Respuestas
- **200 OK: Solicitud exitosa.** Devuelve una lista de ids, cuyos colaboradores cumplen con los criterios.
  Por ej: [1, 2, 3]
- **400 Bad Request: Solicitud incorrecta.** Puede ocurrir si los parámetros no son válidos.
- **500 Internal Server Error: Error en el servidor.** Puede ocurrir si hay un problema interno al procesar la solicitud.
