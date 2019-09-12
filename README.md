# Rest Api Comparison

### Routes

- **GET** "/"
- **GET** "/snippets"; example response: 
```
{
  "snippets": [
    {
      "user": "test",
      "text": "hello"
    },
    {
      "user": "test",
      "text": "world"
    }
  ]
}
```
- **POST** "/snippets"; example body:
```
{
	"snippet":{
		"text":"my snippet"
	}
}
```
- **POST** "/login-register"; 
example body:
```
{
	"user":"user1",
	"password":"1234"
}
```

example response:
```
{
  "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidXNlcjEifQ.uUN6DkADu4HmS5PYvd6jXC5rWhyMy6QvWVAluOQzq8Y"
}
```
