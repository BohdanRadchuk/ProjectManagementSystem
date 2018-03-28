select  sum(salary) from developers 
where id_dev in (select developer_projects.id_dev from developer_projects where developer_projects.id_project =5 );