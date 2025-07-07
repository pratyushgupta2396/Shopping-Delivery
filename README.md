# 🛒 Swiggy InstaMart MVP

A **Minimum Viable Product (MVP)** simulation of **Swiggy InstaMart**, built as a hyperlocal grocery delivery system. This project models key user roles and their real-world operations including order placement, inventory requests, delivery tracking, and platform administration.

---

## 🚀 Features

### 🧍 Shoppers
- Browse products available in their **pincode-specific warehouse**
- Add and remove items from **shopping cart**
- Place orders based on **available inventory**
- **Switch delivery locations** (pincode-based)
- Choose from **multiple payment methods**

### 🏬 Warehouse Admins
- **Request new products** for their warehouse
- **Approve delivery partner** registration requests

### 🚴 Delivery Partners
- **Mark availability** for accepting deliveries
- **Update delivery status** (e.g., In-Progress, Delivered)

### 👨‍💼 App Administrators
- View **warehouse-wise order analytics**
- **Approve/Reject product requests** from warehouse admins

---

## 🛠 Tech Stack (Suggestive)

| Layer       | Technology Options                           |
|-------------|-----------------------------------------------|
| Backend     | Spring Boot / Node.js / Django               |
| Frontend    | React.js / Angular / Vue.js                  |
| Database    | PostgreSQL / MongoDB                         |
| Auth        | JWT / OAuth2                                 |
| Deployment  | Docker, Koyeb, Vercel, Render                |

---

## 📂 Folder Structure (Suggestive)

```plaintext
swiggy-insta-mart-mvp/
├── backend/
│   ├── src/
│   │   ├── controllers/
│   │   ├── models/
│   │   ├── routes/
│   │   └── services/
│   ├── config/
│   ├── .env
│   └── app.js / main.go / manage.py
│
├── frontend/
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   ├── services/
│   │   └── App.js
│   └── public/
│
├── database/
│   ├── schema.sql / migrations/
│   └── seed/
│
├── docker/
│   ├── Dockerfile
│   └── docker-compose.yml
│
├── README.md
└── package.json / pom.xml / requirements.txt
