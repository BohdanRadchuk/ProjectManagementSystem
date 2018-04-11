INSERT INTO developers (firstname, secondaryName, age, gender)
VALUES ('Ivan', 'Ivanow', 26, 'male'),
  ('Oleg', 'Olegov', 30, 'male'),
  ('Karina', 'Karinova', 45, 'female'),
  ('Olexandr', 'Olexandrov', 25, 'male'),
  ('Elena', 'elenova', 33, 'female');
INSERT INTO skills (branch, skill_level)
VALUES ('Java', 'Junior'),
  ('Java', 'Middle'),
  ('Java', 'Senior'),
  ('C++', 'Junior'),
  ('C++', 'Middle'),
  ('C++', 'Senior'),
  ('C#', 'Junior'),
  ('C#', 'Middle'),
  ('C#', 'Senior'),
  ('JS', 'Junior'),
  ('JS', 'Middle'),
  ('JS', 'Senior');
INSERT INTO projects (ProjectName, description)
VALUES ('JavaGame', 'Create online shooter for phones'),
  ('Accountent program', 'program that counts sallary for workers'),
  ('Banking program', 'counts avarage cost of dollar per period'),
  ('Chat', 'Chat for usage inside the company'),
  ('Music player', 'Music player with equalizer for phone');
INSERT INTO companies (CompanyName, CompanyCreationYear)
VALUES ('LuksSoft', 2017),
  ('Cogmizamt Techmology ', 1985),
  ('Capgenimi', 1991),
  ('Hemllet Paskard Emteprpise', 2008);
INSERT INTO customers (CustomerName, StateOrPrivate)
VALUES ('Sergey', FALSE),
  ('Denis', FALSE),
  ('Oleg Vladimirovich', TRUE),
  ('Nadegda Olexandovna', TRUE),
  ('Anton', TRUE);
INSERT INTO developer_projects (id_dev, id_project)
VALUES (1, 1), (1, 3), (2, 4), (3, 1), (3, 5), (4, 2), (4, 5), (5, 2), (5, 3);
INSERT INTO developer_skill (id_dev, id_skill)
VALUES (1, 3), (1, 4), (1, 11), (2, 7), (3, 3), (3, 11), (4, 6), (4, 8), (4, 1), (5, 6), (5, 9), (5, 11);
INSERT INTO companies_projects (id_company, id_project)
VALUES (1, 1), (1, 5), (2, 2), (3, 4), (4, 3);
INSERT INTO customers_projects (id_customer, id_project)
VALUES (1, 5), (2, 1), (2, 5), (3, 2), (4, 4), (5, 3);