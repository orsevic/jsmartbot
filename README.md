# jsmartbot - пока не доработан, нет времени чтобы доработать, нужны желающие
***
Легконастраиваемый и универсальный бот. Спектр применения которого от всевозможных опросов до сайтов знакомств и планировщиков, помощников.
***

Основные функции бота:
1) Легкое заведение фраз бота с графом переходов между ними (фразы могут быть как вопросами, так и фразами не требующими ответа, например "Спасибо", либо какие-то оповещения о персонализированных событиях)
2) Хранение пользовательских ответов, часть ответов могут сохраняться как характеристики пользователя
3) Поиск пользователей по их характеристикам

##### Первое практическое применение: поиск языкового разговориного клуба (или просто собеседника) по уровню английского языка.
На данный момент это предполагается что будет работать так:
* пользователь получает ряд каких-то вопросов, например "Какой у вас уровень английского языка?", "Где бы вы хотели общаться - онлайн или встретится в каком-то публичном месте?", если пользователь выбирает какое-то публичное место, то бот спрашивает либо его название, либо геолокацию
* Далее пользователю предлагается взависимости от варианта онлайн или в публичном месте либо список пользователей с тем же уровнем английского, либо список языковых клубов близких территориально и временем когда эти пользователи хотят общаться или языковые клубы встречаются.
* Пользователь может либо что-то из предложенного и послать уведомление о том что хочет присоединится, либо создать свой языковой клуб. Таким образом его увидят другие пользователи и возможно присоединятся к нему
* Так же пользователи получат уведомления за определенный периуд времени о том что они договорились посетить клуб или пообщаться онлайн с человеком

На данный момент в качестве фронта используется телеграм, так как он позволяет нам пока не думать о реализации клиента. Но мы будем стараться не привязываться к одному клиенту

##### Пример диалога

```
- Hi, I am smartbot!
- What is your name?
    - **John**
- Nice to meet you, John!
- Which english level do you have?
    - beginner
    - pre-intermediate
    - intermediate - **X**
    - upper-intermediate
    - advanced
    - native speaker
- Great! I have 1535 students with this level. 1030 of them wants to speak online and 505 of them wants to speak in speaking club.
- Which type of meetings do you prefer?
    - online
    - offline
    - both - **X**
- Can you send us your location where you prefer to speak offline?
    **location**
- Choose one of them or create your own meeting:
- Jessica (online) (rate 5) - in 5 minutes
- Sara (online) (rate 4) - in 1 hour
- Bruno (online) (rate 5) - at 6PM
- Luke (online)(rate 5) - tomorrow at 10AM
- more online students

- The nearest speaking club (location) (5 persons) - in 5 minutes
- 1 km far from you speaking club (location) (10 persons) - in 5 minutes
- another 1 km far from you speaking club (location) (3 persons) - in 1 hour
- another 1,5 km far from you speaking club (location) (5 persons) - at 7 pm
- more speaking clubs

- or create your own meeting or speaking club

    - **Brumo (online) at 6PM**
- Thanks, wait a minute while Bruno accept you.
- Bruno has accepted you. Do not forget about meeting.
...
- Hi, John. Your meeting with Brono in 15 minutes.
...
- Time to meet Bruno. His account is BLABLABLA
... in 2 hours

- Do you like meeting with Brono?
  - yes
  - no
... if yes
    ** - yes **
- Do you want to save Bruno as prefer students?
    ** - yes **
... if no
    ** no **
- Why?
 - Hi did not come

```
