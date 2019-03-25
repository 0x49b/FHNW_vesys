package ch.fhnw.ds.spark;

public class UserApplication {

	public static void main(String[] args) {
		new UserController(new UserService());
	}

}

/*

curl -i -X GET http://localhost:4567/users
curl -i -X POST -d "name=Peter" -d "email=peter.mueller@fhnw.ch" http://localhost:4567/users

set ID=8e80e0dd-d7f2-40ca-8e7f-a0b18b41f1db

curl -i -X GET http://localhost:4567/users/%ID%
curl -i -X PUT -H "Content-Type: application/json" --data "{\"id\":\"%ID%\",\"name\":\"Peter2\",\"email\":\"peter.mueller@fhnw.ch\"}" http://localhost:4567/users/%ID%
curl -i -X DELETE http://localhost:4567/users/%ID%

*/
