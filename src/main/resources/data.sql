INSERT INTO WINNING_PRODUCER_VIEW(NAME, WINNER)
Select distinct name as name,
                year_winner as winner
from producer
where year_winner is not null
  and name in (SELECT name
               FROM producer
               where year_winner is not null
               GROUP BY name
               HAVING Count(name) > 1)
order by name asc, year_winner;