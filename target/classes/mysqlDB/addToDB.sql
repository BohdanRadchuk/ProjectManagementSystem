alter table developers add salary decimal NOT NULL;
update developers set salary = 5000 where id_dev = 1;
update developers set salary = 1200 where id_dev = 2;
update developers set salary = 4500 where id_dev = 3;
update developers set salary = 4800 where id_dev = 4;
update developers set salary = 5500 where id_dev = 5;

alter table projects add cost int NOT NULL;
update projects set cost = 58000 where id_project = 1;
update projects set cost = 200000 where id_project = 2;
update projects set cost = 350000 where id_project = 3;
update projects set cost = 8000 where id_project = 4;
update projects set cost = 70500 where id_project = 5;