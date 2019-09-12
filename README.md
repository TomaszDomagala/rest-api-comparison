# Rest Api Comparison

### Routes

- **GET** "/"
- **GET** "/snippets"; example response: 
```
{
  "snippets" : [ {
    "text" : "hello"
  }, {
    "text" : "world"
  } ]
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
