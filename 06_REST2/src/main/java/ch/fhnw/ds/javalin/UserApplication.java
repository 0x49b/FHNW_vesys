package ch.fhnw.ds.javalin;

import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.patch;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.crud;


import io.javalin.Javalin;

public class UserApplication {

	public static void main(String[] args) {
        Javalin app = Javalin.create().port(7000).start();
        
        UserController controller = new UserController(new UserService());

        app.routes(() -> {
            path("users", () -> {
                get(controller::getAllUsers);
                post(controller::createUser);
                path(":id", () -> {
                    get(controller::getUser);
                    patch(controller::updateUser);
                    delete(controller::deleteUser);
                });
            });
        });
        
//        app.routes(() -> {
//        	crud("users/:id", controller);
//        });

	}	
}

/*

curl -i -X GET http://localhost:7000/users
curl -i -X POST -d "name=Peter" -d "email=peter.mueller@fhnw.ch" http://localhost:7000/users

set ID=863e1cea-7c2c-4ba0-9b4e-e58769c3d7c7

curl -i -X GET http://localhost:7000/users/%ID%
curl -i -X PATCH -H "Content-Type: application/json" --data "{\"id\":\"%ID%\",\"name\":\"Peter\",\"email\":\"peter.mueller@fhnw.ch\"}" http://localhost:7000/users/%ID%
curl -i -X DELETE http://localhost:7000/users/%ID%

*/
