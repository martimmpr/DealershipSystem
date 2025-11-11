# Dealership System Backend
Java-based backend system for vehicle dealership management with GraphQL API and Image Storage.

## Tech Stack
- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Validation**
- **Spring GraphQL**
- **Spring Data JPA**
- **PostgreSQL**
- **MinIO**
- **Maven**
- **Docker**

## Database Schema

### Vehicles Table
| Column | Type | Description | Constraints |
|--------|------|-------------|-------------|
| `id` | UUID | Primary key | NOT NULL, Auto-generated |
| `brand` | VARCHAR | Vehicle brand | NOT NULL |
| `model` | VARCHAR | Vehicle model | NOT NULL |
| `segment` | VARCHAR | Vehicle segment | NOT NULL, Valid segments only |
| `price` | DOUBLE | Vehicle price | NOT NULL, Positive |
| `kms` | INTEGER | Kilometers | NOT NULL, >= 0 |
| `month` | INTEGER | Manufacturing month | NOT NULL, 1-12 |
| `year` | INTEGER | Manufacturing year | NOT NULL, >= 1900 |
| `fuel` | VARCHAR | Fuel type | NOT NULL, Valid fuel types only |
| `cm3` | INTEGER | Engine displacement | Nullable, Required for non-electric |
| `kwh` | DOUBLE | Battery capacity | Nullable, Required for electric/hybrid |
| `hp` | INTEGER | Horsepower | NOT NULL, Positive |
| `consumption` | VARCHAR | Fuel/energy consumption | NOT NULL |

### Valid Fuel Types
- `gasoline` - Gasoline engines (requires cm3)
- `diesel` - Diesel engines (requires cm3)  
- `electric` - Electric vehicles (requires kwh, cm3 not allowed)
- `gasoline_hybrid` - Gasoline hybrid (requires both cm3 and kwh)
- `diesel_hybrid` - Diesel hybrid (requires both cm3 and kwh)
- `hybrid_plugin` - Plug-in hybrid (requires both cm3 and kwh)

### Valid Vehicle Segments
- `cabrio` - Convertible vehicles
- `estate` - Station wagons
- `city` - City cars
- `coupe` - Two-door sports cars
- `minivan` - Large family vehicles
- `sedan` - Four-door sedans
- `suv` - Sport utility vehicles
- `offroad` - Off-road specialized vehicles
- `utilitarian` - Commercial/utility vehicles

## API Documentation

### Vehicles Management

#### GraphQL API
**Endpoint:** `http://localhost:8080/graphql`  
**GraphiQL:** `http://localhost:8080/graphiql`

**Create Vehicle:**
```graphql
mutation {
  createVehicle(input: {
    brand: "Toyota"
    model: "Corolla"
    segment: SEDAN
    price: 25000.0
    kms: 0
    month: 6
    year: 2024
    fuel: GASOLINE
    cm3: 1800
    hp: 140
    consumption: "6.5L/100km"
  }) {
    id
    brand
    model
    segment
    price
    imageUrls
  }
}
```

**Get Vehicle by ID:**
```graphql
query {
  getVehicleById(id: "123e4567-e89b-12d3-a456-426614174000") {
    id
    brand
    model
    segment
    price
    imageUrls
  }
}
```

**Get All Vehicles:**
```graphql
query {
  getAllVehicles {
    id
    brand
    model
    segment
    price
    kms
    month
    year
    fuel
    cm3
    kwh
    hp
    consumption
    imageUrls
  }
}
```

**Update Vehicle:**
```graphql
mutation {
  updateVehicle(input: {
    id: "123e4567-e89b-12d3-a456-426614174000"
    price: 23000.0
    kms: 5000
  }) {
    id
    brand
    model
    price
    kms
  }
}
```

**Delete Vehicle:**
```graphql
mutation {
  deleteVehicle(id: "123e4567-e89b-12d3-a456-426614174000")
}
```

## Images Management

### Rest API
**Base URL:** `http://localhost:8080/api/vehicles`

#### Image Operations

**Upload Vehicle Image:**
```bash
POST /{vehicleId}/images
Content-Type: multipart/form-data

curl -X POST http://localhost:8080/api/vehicles/{vehicleId}/images \
  -F "image=@/path/to/image.jpg"
```

**Get Specific Image:**
```bash
GET /{vehicleId}/images/{imageNumber}

curl -X GET http://localhost:8080/api/vehicles/{vehicleId}/images/{imageId}
```

**Get Vehicle Images List:**
```bash
GET /{vehicleId}/images

curl -X GET http://localhost:8080/api/vehicles/{vehicleId}/images
```

**Delete Vehicle Image:**
```bash
DELETE /{vehicleId}/images/{imageNumber}

curl -X DELETE http://localhost:8080/api/vehicles/{vehicleId}/images/{imageId}
```

## Quick Start

### Development

1. **Configure environment variables:**
```bash
cd backend

cp .env.example .env

nano .env
```

2. **Start services with Docker Compose:**
```bash
docker compose up -d
```

3. **Run the application:**
```bash
./mvnw spring-boot:run
```

4. **Access the application:**
- GraphiQL: http://localhost:8080/graphiql
- MinIO Console: http://localhost:9001

#### Stop Services

```bash
cd backend

docker compose down
```

#### Clean Up (Remove volumes)

```bash
cd backend

docker-compose down -v
```