FROM openjdk
COPY ./target/ProductManagementSystem-0.0.1-SNAPSHOT.jar ./
CMD ["java","-jar","ProductManagementSystem-0.0.1-SNAPSHOT.jar"]