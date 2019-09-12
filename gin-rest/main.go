package main

import (
	"github.com/gin-gonic/gin"
)

type Snippet struct {
	Text string `json:"text"`
}

type PostSnippet struct {
	Snippet Snippet `json:"snippet"`
}

var snippets = []Snippet{
	{"hello"},
	{"world"},
}

func main() {
	router := gin.Default()
	router.GET("/", func(context *gin.Context) {
		context.JSON(200, gin.H{
			"Hello": "Gin",
		})
	})
	router.GET("/snippets", func(context *gin.Context) {
		context.JSON(200, gin.H{
			"snippets": snippets,
		})
	})
	router.POST("/snippets", func(context *gin.Context) {
		var post PostSnippet
		err := context.BindJSON(&post)
		if err != nil {
			context.JSON(400, gin.H{
				"OK":      false,
				"message": err.Error(),
			})
		} else if post.Snippet.Text == "" {
			context.JSON(400, gin.H{
				"OK":      false,
				"message": "No Empty Snippets Allowed",
			})
		} else {
			snippets = append(snippets, post.Snippet)
			context.JSON(200, gin.H{
				"OK": true,
			})
		}

	})

	router.Run() // listen and serve on 0.0.0.0:8080
}
