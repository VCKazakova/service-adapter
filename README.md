### service-adapter

Микросервис «Adapter», который принимает сообщение из «Service А», производит обогащение данными погоды и передает его в
«Service B».

````json
{
  "openapi": "3.0.1",
  "info": {
    "title": "OpenAPI definition",
    "version": "v0"
  },
  "servers": [
    {
      "url": "http://localhost:8080",
      "description": "Generated server url"
    }
  ],
  "paths": {
    "/v1/messages": {
      "post": {
        "tags": [
          "message-a-controller-impl"
        ],
        "operationId": "richMessage",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/RqDtoMessageA"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK"
          }
        }
      }
    }
  },
  "components": {
    "schemas": {
      "RqDtoCoordinates": {
        "required": [
          "latitude",
          "longitude"
        ],
        "type": "object",
        "properties": {
          "latitude": {
            "type": "string"
          },
          "longitude": {
            "type": "string"
          }
        }
      },
      "RqDtoMessageA": {
        "required": [
          "Ing",
          "msg"
        ],
        "type": "object",
        "properties": {
          "latitude": {
            "type": "string"
          },
          "longitude": {
            "type": "string"
          },
          "ing": {
            "type": "string",
            "enum": [
              "ru",
              "en",
              "es"
            ]
          },
          "msg": {
            "type": "string"
          },
          "Ing": {
            "type": "string",
            "enum": [
              "ru",
              "en",
              "es"
            ]
          },
          "coordinates": {
            "$ref": "#/components/schemas/RqDtoCoordinates"
          }
        }
      }
    }
  }
}
````
OpenApi доступно по ссылке при запуске приложения: http://localhost:8080/v3/api-docs