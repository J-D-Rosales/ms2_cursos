# ms2_cursos

Welcome to **ms2_cursos**!  
This repository is a Java-based microservice designed to manage courses, making it ideal for educational platforms, training services, and scalable learning environments.

## Features

- **Course Management**: Easily add, update, and delete courses.
- **RESTful API**: Exposes endpoints for integration with other services or frontends.
- **Scalable Architecture**: Built with microservices principles for modularity and scalability.
- **Data Persistence**: Connects to a database for reliable storage and retrieval of course information.
- **Extensible**: Ready to integrate with authentication, user management, and other educational services.

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven or Gradle
- (Optional) Docker for containerization

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/J-D-Rosales/ms2_cursos.git
   cd ms2_cursos
   ```

2. **Build the project**
   ```bash
   mvn clean install
   # Or if using Gradle:
   # gradle build
   ```

3. **Run the microservice**
   ```bash
   mvn spring-boot:run
   # Or with Gradle:
   # gradle bootRun
   ```

### Configuration

- The application can be configured using the `application.properties` or `application.yml` file.
- Set your database connection details and other environment variables as needed.

## Usage

Once running, the microservice exposes REST endpoints for managing courses.
Example API endpoints:

- `GET /courses` – List all courses
- `POST /courses` – Create a new course
- `PUT /courses/{id}` – Update course details
- `DELETE /courses/{id}` – Remove a course

## Contributing

Contributions are welcome!  
Feel free to open issues, submit pull requests, or suggest new features.

1. Fork the repo
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

Distributed under the MIT License.  
See `LICENSE` for more information.

## Contact

Created by [J-D-Rosales](https://github.com/J-D-Rosales)  
For questions or support, open an issue on GitHub.

---

Happy coding!
