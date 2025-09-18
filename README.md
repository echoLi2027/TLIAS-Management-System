# TLIAS - Intelligent Learning Assistant System

A full-stack enterprise management system built with Spring Boot and Vue.js, featuring comprehensive employee and student management capabilities with cloud storage integration and advanced logging.

## 🚀 Technology Stack

### Backend Technologies
- **Java 17** - Core programming language
- **Spring Boot 3.x** - Microservice framework
- **Spring AOP** - Aspect-oriented programming for cross-cutting concerns
- **MyBatis** - Persistence framework for database operations
- **JWT (JSON Web Tokens)** - Stateless authentication mechanism
- **MySQL 8.0** - Relational database management system
- **Aliyun OSS** - Object storage service for file management
- **Maven** - Project build and dependency management

### Frontend Technologies
- **Vue.js 3** - Progressive JavaScript framework
- **Element Plus** - Enterprise-grade UI component library
- **Axios** - Promise-based HTTP client
- **Vue Router** - Official routing library
- **ECharts** - Professional data visualization library
- **Nginx** - Web server for frontend deployment

### DevOps & Deployment
- **Docker** - Application containerization
- **Docker Compose** - Multi-container orchestration
- **Linux** - Production server environment
- **Git** - Version control system

## 📋 Core Features

### Authentication & Authorization
- JWT-based stateless authentication
- Token expiration and refresh mechanism
- Request interceptor for token validation
- 401 unauthorized response for invalid tokens

### Department Management
- CRUD operations with RESTful APIs
- Hierarchical department structure
- Employee-department relationships
- Cascade operations for related data

### Employee Management
- Advanced search with multiple filters
- Pagination for large datasets
- Work experience tracking
- Batch operations for efficiency
- Profile image upload to Aliyun OSS

### Class Management
- Subject-based class creation
- Instructor assignment
- Status tracking (not started, ongoing, completed)
- Time-based filtering and queries

### Student Management
- Education level categorization
- Violation tracking system
- Score deduction mechanism
- Class enrollment management
- Batch import/export capabilities

### Statistical Reports
- Real-time data visualization with ECharts
- Employee distribution by gender and position
- Student statistics by education level
- Class enrollment analytics
- Exportable report generation

### System Monitoring
- AOP-based operation logging
- Method execution time tracking
- Parameter and return value recording
- User action audit trail
- Performance metrics collection

## 🛠️ Installation Guide

### Prerequisites
```bash
# Required software versions
Java 17+
Node.js 16+
MySQL 8.0+
Maven 3.6+
Docker 20.10+
Docker Compose 2.0+
```

### Backend Setup

1. **Clone the repository**
```bash
git clone https://github.com/echoLi2027/tlias-system.git
cd tlias-system
```

2. **Configure database**
```sql
CREATE DATABASE tlias CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

3. **Configure application properties**
```yaml
# backend/src/main/resources/application.yml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/tlias?serverTimezone=UTC
    username: root
    password: your_password

# Aliyun OSS configuration
aliyun:
  oss:
    endpoint: oss-cn-hangzhou.aliyuncs.com
    accessKeyId: your_access_key
    accessKeySecret: your_secret_key
    bucketName: tlias-storage

# JWT configuration
jwt:
  signKey: your_secret_key
  expire: 43200000  # 12 hours in milliseconds
```

4. **Build and run**
```bash
cd backend
mvn clean package
java -jar target/tlias-backend.jar
```

### Frontend Setup

1. **Install dependencies**
```bash
cd frontend
npm install
```

2. **Configure API endpoint**
```javascript
// src/utils/request.js
const baseURL = process.env.VUE_APP_BASE_API || 'http://localhost:8080'
```

3. **Development server**
```bash
npm run serve
```

4. **Production build**
```bash
npm run build
# Output in dist/ directory
```

## 🐳 Docker Deployment

### Backend Dockerfile
```dockerfile
# backend/Dockerfile
FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/tlias-backend.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080
```

### Frontend Nginx Configuration
```nginx
# frontend/nginx.conf
server {
    listen 80;
    server_name localhost;
    
    location / {
        root /usr/share/nginx/html;
        index index.html;
        try_files $uri $uri/ /index.html;
    }
    
    location /api {
        proxy_pass http://backend:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
    }
}
```

### Docker Compose Setup
```yaml
# docker-compose.yml
version: '3.8'
services:
  mysql:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: root123
      MYSQL_DATABASE: tlias
    volumes:
      - mysql_data:/var/lib/mysql
    ports:
      - "3306:3306"
    
  backend:
    build: ./backend
    depends_on:
      - mysql
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/tlias
    ports:
      - "8080:8080"
    
  frontend:
    build: ./frontend
    ports:
      - "80:80"
    depends_on:
      - backend

volumes:
  mysql_data:
```

### Deployment Commands
```bash
# Build all services
docker-compose build

# Start all services
docker-compose up -d

# View logs
docker-compose logs -f

# Stop all services
docker-compose down
```

## 📁 Project Structure

```
tlias-system/
├── backend/
│   ├── src/main/java/com/itheima/tlias/
│   │   ├── aop/
│   │   │   ├── LogAspect.java           # Operation logging aspect
│   │   │   └── TimeAspect.java          # Performance monitoring
│   │   ├── config/
│   │   │   ├── OSSConfig.java           # Aliyun OSS configuration
│   │   │   └── WebConfig.java           # Web MVC configuration
│   │   ├── controller/
│   │   │   ├── DeptController.java      # Department APIs
│   │   │   ├── EmpController.java       # Employee APIs
│   │   │   ├── LoginController.java     # Authentication
│   │   │   └── UploadController.java    # File upload
│   │   ├── filter/
│   │   │   └── JwtAuthFilter.java       # JWT validation filter
│   │   ├── mapper/
│   │   │   └── *.java                   # MyBatis mappers
│   │   ├── pojo/
│   │   │   ├── entity/                  # Database entities
│   │   │   ├── dto/                     # Data transfer objects
│   │   │   └── vo/                      # View objects
│   │   ├── service/
│   │   │   └── impl/                    # Service implementations
│   │   ├── utils/
│   │   │   ├── JwtUtils.java           # JWT utilities
│   │   │   └── OSSUtils.java           # OSS utilities
│   │   └── exception/
│   │       └── GlobalExceptionHandler.java
│   ├── src/main/resources/
│   │   ├── mapper/                      # MyBatis XML files
│   │   └── application.yml
│   ├── Dockerfile
│   └── pom.xml
├── frontend/
│   ├── src/
│   │   ├── api/                         # API service modules
│   │   ├── views/                       # Vue components
│   │   ├── router/                      # Route configuration
│   │   ├── store/                       # Vuex state management
│   │   └── utils/
│   │       ├── request.js               # Axios configuration
│   │       └── auth.js                  # Token management
│   ├── public/
│   ├── nginx.conf
│   ├── Dockerfile
│   └── package.json
├── database/
│   ├── schema.sql                       # Database schema
│   └── init-data.sql                    # Initial data
├── docker-compose.yml
└── README.md
```

## 🔑 API Authentication Flow

### 1. Login Process
```http
POST /login
Content-Type: application/json

{
  "username": "admin",
  "password": "123456"
}
```

### 2. Token Response
```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "id": 1,
    "username": "admin",
    "name": "Administrator",
    "token": "eyJhbGciOiJIUzI1NiJ9..."
  }
}
```

### 3. Authenticated Requests
```http
GET /emps
Headers:
  token: eyJhbGciOiJIUzI1NiJ9...
```

## 🎯 AOP Logging Implementation

### Log Annotation
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    String value() default "";
}
```

### Log Aspect
```java
@Aspect
@Component
public class LogAspect {
    @Autowired
    private OperateLogMapper logMapper;
    
    @Around("@annotation(log)")
    public Object recordLog(ProceedingJoinPoint pjp, Log log) throws Throwable {
        long startTime = System.currentTimeMillis();
        
        // Execute method
        Object result = pjp.proceed();
        
        // Record execution details
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setClassName(pjp.getTarget().getClass().getName());
        operateLog.setMethodName(pjp.getSignature().getName());
        operateLog.setMethodParams(Arrays.toString(pjp.getArgs()));
        operateLog.setReturnValue(result.toString());
        operateLog.setCostTime(System.currentTimeMillis() - startTime);
        
        logMapper.insert(operateLog);
        return result;
    }
}
```

## 📤 File Upload with Aliyun OSS

### Upload Service
```java
@Service
public class OSSService {
    @Autowired
    private OSSClient ossClient;
    
    public String upload(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        ossClient.putObject(bucketName, fileName, file.getInputStream());
        return "https://" + bucketName + ".oss-cn-hangzhou.aliyuncs.com/" + fileName;
    }
}
```

## 💻 Development Workflow

### Local Development Setup

1. **Backend Development**
```bash
# Start MySQL locally
docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root --name mysql mysql:8.0

# Run Spring Boot with hot reload
cd backend
mvn spring-boot:run

# View logs in terminal and check log files
tail -f logs/tlias.log
```

2. **Frontend Development**
```bash
cd frontend
npm run serve
# Access at http://localhost:8081
# Vue DevTools for debugging
```

3. **Logging Best Practices**
```java
@Slf4j
@Service
public class EmployeeService {
    
    public void processEmployee(Employee emp) {
        log.info("Processing employee: {}", emp.getId());
        
        try {
            // Business logic
            log.debug("Employee details: {}", emp);
            
            // Database operation
            empMapper.insert(emp);
            log.info("Employee {} saved successfully", emp.getName());
            
        } catch (DataAccessException e) {
            log.error("Failed to save employee: {}", emp, e);
            throw new BusinessException("Employee save failed", e);
        }
    }
}
```

### Daily Development Routine

```bash
# Morning: Pull latest changes
git pull origin develop

# Start development services
docker-compose up mysql redis
mvn spring-boot:run
npm run serve

# Test API changes in Apifox
# Create/update test cases
# Run automated test suite

# Evening: Commit completed features
git add .
git commit -m "feat: implement employee batch import"
git push origin feature/current-feature

# Check logs for any issues
grep ERROR logs/tlias-*.log
```

## 📊 Performance Optimization

- **Database**: Index optimization on frequently queried columns
- **Caching**: Redis for session management and frequent queries
- **Pagination**: Server-side pagination for large datasets
- **File Upload**: Async upload with progress tracking
- **Connection Pool**: HikariCP for database connections

## 🔍 Monitoring & Logging

### Logging with SLF4J
The project uses **@Slf4j** annotation (Lombok) for comprehensive logging:

```java
@Slf4j
@RestController
public class EmployeeController {
    
    @GetMapping("/emps")
    public Result list(PageBean pageBean) {
        log.info("Query employees with params: {}", pageBean);
        try {
            PageResult result = empService.page(pageBean);
            log.debug("Query returned {} records", result.getTotal());
            return Result.success(result);
        } catch (Exception e) {
            log.error("Error querying employees", e);
            throw e;
        }
    }
}
```

### Logback Configuration
```xml
<!-- src/main/resources/logback-spring.xml -->
<configuration>
    <!-- Console output -->
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>
    
    <!-- File output with daily rotation -->
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>logs/tlias.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>logs/tlias-%d{yyyy-MM-dd}.log</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>
    
    <root level="INFO">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="FILE"/>
    </root>
</configuration>
```

### Troubleshooting Guide

Common issues and solutions:

1. **JWT Token Expired**
   ```
   ERROR: JWT expired at 2024-01-20T10:30:00Z
   Solution: Re-authenticate to get a new token
   ```

2. **Database Connection Issues**
   ```
   ERROR: Failed to obtain JDBC Connection
   Check: MySQL service status, credentials, network connectivity
   ```

3. **File Upload Failures**
   ```
   ERROR: Aliyun OSS upload failed
   Check: OSS credentials, bucket permissions, network access
   ```

4. **CORS Issues**
   ```
   ERROR: CORS policy blocked request
   Solution: Configure allowed origins in WebConfig
   ```

## 🧪 API Testing with Apifox

The project uses **Apifox** for API testing and documentation. Apifox provides:
- Automated API testing
- Environment variable management
- Mock server capabilities
- Team collaboration features

### Import API Collection
```json
// Import the Apifox collection from: /docs/tlias-apifox-collection.json
// Configure environments:
// - Development: http://localhost:8080
// - Production: https://api.your-domain.com
```

### Test Scenarios
1. **Authentication Flow**: Login → Store Token → Use in subsequent requests
2. **CRUD Operations**: Complete test suites for each module
3. **Error Handling**: Test 400, 401, 404, 500 responses
4. **Performance Testing**: Batch operations and pagination

### Key Endpoints
| Module | Method | Endpoint | Description |
|--------|--------|----------|-------------|
| Auth | POST | `/login` | User authentication |
| Dept | GET | `/depts` | List departments |
| Emp | GET | `/emps` | Query employees with pagination |
| Upload | POST | `/upload` | File upload to OSS |
| Report | GET | `/report/*` | Statistical data |

## 🔄 Version Control & CI/CD

### Git Workflow
The project uses **GitHub** and **GitLab** for version control with a feature-based development approach:

```bash
# Create feature branch for new functionality
git checkout -b feature/employee-management

# Regular commits after completing each part
git add .
git commit -m "feat: add employee search functionality"
git commit -m "fix: correct pagination logic in employee list"
git commit -m "docs: update API documentation for employee endpoints"

# Push to remote repository
git push origin feature/employee-management

# Create merge request/pull request for review
```

### Commit Convention
Following conventional commits for clear history:
- `feat:` New feature implementation
- `fix:` Bug fixes
- `docs:` Documentation updates
- `style:` Code formatting (no logic change)
- `refactor:` Code restructuring
- `test:` Adding or updating tests
- `chore:` Maintenance tasks

### Branch Strategy
```
main
├── develop
│   ├── feature/employee-management
│   ├── feature/student-module
│   └── feature/report-generation
└── hotfix/critical-bug-fix
```

### GitLab CI/CD Pipeline (.gitlab-ci.yml)
```yaml
stages:
  - build
  - test
  - package
  - deploy

variables:
  MAVEN_OPTS: "-Dmaven.repo.local=.m2/repository"

cache:
  paths:
    - .m2/repository/
    - frontend/node_modules/

build-backend:
  stage: build
  script:
    - cd backend
    - mvn clean compile
  artifacts:
    paths:
      - backend/target/

test-backend:
  stage: test
  script:
    - cd backend
    - mvn test
  dependencies:
    - build-backend

package-backend:
  stage: package
  script:
    - cd backend
    - mvn package -DskipTests
  artifacts:
    paths:
      - backend/target/*.jar
  only:
    - main
    - develop

build-frontend:
  stage: build
  script:
    - cd frontend
    - npm install
    - npm run build
  artifacts:
    paths:
      - frontend/dist/

deploy:
  stage: deploy
  script:
    - docker-compose build
    - docker-compose up -d
  only:
    - main
```

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👤 Author

**echoLi2027** - [GitHub Profile](https://github.com/echoLi2027)

## 🎓 Learning Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Vue.js Guide](https://vuejs.org/guide/)
- [MyBatis Documentation](https://mybatis.org/mybatis-3/)
- [Docker Best Practices](https://docs.docker.com/develop/dev-best-practices/)
- [JWT Introduction](https://jwt.io/introduction)

---

For questions or support, please open an issue on GitHub.
