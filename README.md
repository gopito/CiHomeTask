[![CircleCI](https://circleci.com/gh/gopito/CiHomeTask.svg?style=svg)](https://circleci.com/gh/gopito/CiHomeTask)
## Домашнее задание по тестированию и CI
Для выполнения задания надо форкнуть себе репозиторий и поправить ошибки в приложении, чтобы все тесты
проходили. В репозитории 4 теста, т.е. надо найти и поправить 4 ошибки.
1. Форкнуть репозиторий.
2. Подключить его к  [cirlceCI](https://circleci.com). Можно залогиниться через гитхаб и дать доступ
к своему аккаунту на гитхаб.
3. Затем надо добавить форкнутый репозиторий. <space><space>
<div id="container">
<img src="pictures/choose_project.png" width="1200px"/>
</div>
4. При первом заходе в __circleCI__ сразу же начнется билд и он будет красным.
<div id="container">
<img src="pictures/failed_builds.png" width="1200px"/>
</div>
5. Если щелкнуть на одном из билдов, то можно увидеть на чем завалились тесты. Должно быть 4 падения.
<div id="container">
<img src="pictures/failed_test.png" width="1200px"/>
</div>
6. На вкладке __Artifacts__ можно скачать сбилженый apk и результаты тестов.
<div id="container">
<img src="pictures/artifacts.png" width="1200px"/>
</div>
7. При каждом пуше на гитхаб запускается билд в __circleCI__. Все настройки для запуска лежат здесь
[a circleCI settings](https://github.com/gopito/CiHomeTask/blob/master/.circleci/config.yml)


Смысл домашки в том чтобы поправить приложение так чтобы все тесты проходили. Это будет видно на 
бэйдже в репозитории. 
Тестировать конечно можно локально и это тесты для которых не нужен эмулятор,
они будут проходить быстро.
Когда тесты пройдут локально, останется только пушнуть.
