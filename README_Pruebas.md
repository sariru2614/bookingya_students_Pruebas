# 🚀 BookingYa - Automatización de Pruebas (TDD, BDD, ATDD + CI)

Este proyecto implementa una estrategia completa de pruebas para una API de reservas (**BookingYa**), integrando:

- TDD (Test Driven Development)  
- BDD (Behavior Driven Development)  
- ATDD (Acceptance Test Driven Development)  
- CI/CD con GitHub Actions  

---

## 🧱 Estructura del proyecto

bookingya_students → Aplicación base  
UnitTestBookinYa → Pruebas TDD  
PruebaBooking → Pruebas BDD  
PruebaBookingATDD → Pruebas ATDD  
.github/workflows → Pipeline CI  

---

## 🧪 Estrategia de pruebas

### TDD
Valida la lógica interna del sistema.

### BDD
Valida el comportamiento funcional mediante Gherkin.

### ATDD
Valida criterios de aceptación del negocio.
*Para la automatización de estas pruebas se utilizó el framework Serenity, el cual permite ejecutar los escenarios y generar reportes detallados que evidencian el cumplimiento de los criterios de aceptación.

---

## 🔁 Integración Continua

Pipeline en GitHub Actions que ejecuta:

1. Base de datos PostgreSQL  
2. Aplicación Spring Boot  
3. Pruebas TDD  
4. Pruebas BDD  
5. Pruebas ATDD  
6. Reportes Serenity  

---

## ▶️ Ejecución manual

GitHub → Actions → CI - BookingYa Tests → Run workflow

Permite ejecutar pruebas sin commit y con datos dinámicos.

---

## 🧩 Variables por consola

BDD:
- guestIdentification
- guestName
- guestEmail
- roomCode
- roomName

ATDD:
- atddGuestIdentification
- atddGuestName
- atddGuestEmail
- atddRoomCode
- atddRoomName

---

## 📊 Reportes

Se generan reportes Serenity con:

- Resultados de pruebas  
- Logs de ejecución  
- Evidencia  

---


## 🚀 Valor

- Automatización completa  
- Datos dinámicos  
- Cobertura (TDD + BDD + ATDD)
