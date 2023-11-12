# RankingBookService
## RabbitMQ
* getBook
  * Queue: GetBookQueue
  * Exchange: BookExchange
  * Routing key: getbook
* sortBookView
  * Queue: SortBookViewQueue
  * Exchange: BookExchange
  * Routing key: descbookview
* recommendBook
  * Queue: recommendQueue
  * Exchange: BookExchange
  * Routing key: recommend
* homebook
 * Queue: RankBookTypeQueue
 * Exchange: BookExchange
 * Routing key: rankbytype
## Path
* getBook
  * http://localhost:8082/book-service/getBook
* sortBookView
  * http://localhost:8082/book-service/getPopularView/Novel/Romantic
* recommendBook
  * http://localhost:8082/book-service/getRecommendBook/Manga
