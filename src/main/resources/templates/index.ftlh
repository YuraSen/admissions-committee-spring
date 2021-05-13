<head>
    <meta charset="utf-8">
    <#import "/spring.ftl" as spring/>

    <#include "fragments/bootsrap-links.ftl"/>

</head>
<body>
<#include "fragments/nav-bar.ftl" />


<br>
<br>
<br>
<div class="container">

    <p style="line-height: 1; text-align: left;"><strong><span
                    style="font-family: Verdana, Geneva, sans-serif; font-size: 14px;">Приймальна комісія&nbsp;</span></strong>
    </p>
    <p style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                    style="font-family: Verdana, Geneva, sans-serif;">Система має перелік факультетів, для якого необхідно реалізувати можливість сортування:</span></span>
    </p>
    <ul style="list-style-type: circle;">
        <li style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                        style="font-family: Verdana, Geneva, sans-serif;"> - по імені (a-z, z-a);&nbsp;</span></span>
        </li>
        <li style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                        style="font-family: Verdana, Geneva, sans-serif;">&nbsp;- за кількістю бюджетних місць;</span></span>
        </li>
        <li style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                        style="font-family: Verdana, Geneva, sans-serif;">&nbsp;- за загальною кількістю місць. </span></span>
        </li>
    </ul>
    <p style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                    style="font-family: Verdana, Geneva, sans-serif;">Абітурієнт реєструється в системі. Під час реєстрації необхідно ввести ПІБ, email, місто, область, назву навчального закладу (опціонально: прикріпити скан атестату з оцінками).</span></span>
    </p>
    <p style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                    style="font-family: Verdana, Geneva, sans-serif;">&nbsp;Абітурієнт може зареєструватися на один або декілька факультетів. При реєстрації на факультет студент вводить результати з відповідних предметів, а також оцінки з атестату.</span></span>
    </p>
    <p style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                    style="font-family: Verdana, Geneva, sans-serif;">&nbsp;Адміністратор системи володіє правами:</span></span>
    </p>
    <ul style="list-style-type: circle;">
        <li style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                        style="font-family: Verdana, Geneva, sans-serif;">&nbsp;- додавання, видалення або редагування факультету; &nbsp;</span></span>
        </li>
        <li style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                        style="font-family: Verdana, Geneva, sans-serif;">- блокування або &nbsp;розблокування абітурієнта;&nbsp;</span></span>
        </li>
        <li style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                        style="font-family: Verdana, Geneva, sans-serif;">&nbsp;- додавання результатів абітурієнтів до Відомості;</span></span>
        </li>
        <li style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                        style="font-family: Verdana, Geneva, sans-serif;">&nbsp;- фіналізації Відомості на зарахування. </span></span>
        </li>
    </ul>
    <p style="line-height: 1; text-align: left;"><span
                style="font-family: Verdana, Geneva, sans-serif; font-size: 12px;">Після фіналізації відомості система підраховує суму балів і визначає абітурієнтів, зарахованих до навчального закладу на бюджетні місця, на контракт.</span></p>
    <p style="line-height: 1; text-align: left;"><span
                style="font-family: Verdana, Geneva, sans-serif; font-size: 12px;">       (За бажанням додати оповіщення про результат зарахування на певну форму навчання, а також не зарахування за допомогою відправки email абітурієнтові).</span>
    </p>

</div>
<br>
<br>
</body>
