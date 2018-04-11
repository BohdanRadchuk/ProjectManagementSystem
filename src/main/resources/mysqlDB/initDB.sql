CREATE SCHEMA homeworkOneDB;
USE homeworkOneDB;
CREATE TABLE developers (
  id_dev        INT AUTO_INCREMENT PRIMARY KEY,
  firstName     VARCHAR(100) NOT NULL,
  secondaryName VARCHAR(100) NOT NULL,
  age           INT          NOT NULL,
  gender        VARCHAR(50)  NOT NULL
);
CREATE TABLE skills (
  id_skill    INT                                                                                                                                                                                                                                                                                                               AUTO_INCREMENT PRIMARY KEY,
  branch      ENUM ('Java', 'C++', 'C#', 'JS')    NOT NULL                                                                                                                                                                                                                                                                         DEFAULT 'Java',
  skill_level ENUM ('Junior', 'Middle', 'Senior') NOT NULL                                                                                                                                                                                                                                                                      DEFAULT 'Junior'
);
CREATE TABLE projects (
  id_project  INT AUTO_INCREMENT PRIMARY KEY,
  ProjectName VARCHAR(256)  NOT NULL,
  description VARCHAR(1024) NOT NULL
);
CREATE TABLE companies (
  id_company          INT AUTO_INCREMENT PRIMARY KEY,
  CompanyName VARCHAR(256) NOT NULL,
  CompanyCreationYear YEAR NOT NULL
);
CREATE TABLE customers (
  id_customer    INT AUTO_INCREMENT PRIMARY KEY,
  CustomerName VARCHAR(256) NOT NULL,
  StateOrPrivate BOOLEAN    NOT NULL
);
CREATE TABLE developer_projects (
  id_dev     INT NOT NULL,
  id_project INT NOT NULL,
  FOREIGN KEY (id_dev)
  REFERENCES developers (id_dev),
  FOREIGN KEY (id_project)
  REFERENCES projects (id_project),
  CONSTRAINT PK_DEV_PR PRIMARY KEY (id_dev, id_project)
);
CREATE TABLE developer_skill (
  id_dev   INT NOT NULL,
  id_skill INT NOT NULL,
  FOREIGN KEY (id_dev)
  REFERENCES developers (id_dev),
  FOREIGN KEY (id_skill)
  REFERENCES skills (id_skill),
  CONSTRAINT PK_DEV_SKILL PRIMARY KEY (id_dev, id_skill)
);
CREATE TABLE companies_projects (
  id_company INT NOT NULL,
  id_project INT NOT NULL,
  FOREIGN KEY (id_company)
  REFERENCES companies (id_company),
  FOREIGN KEY (id_project)
  REFERENCES projects (id_project),
  CONSTRAINT PK_COM_PR PRIMARY KEY (id_company, id_project)
);
CREATE TABLE customers_projects (
  id_customer INT NOT NULL,
  id_project  INT NOT NULL,
  FOREIGN KEY (id_customer)
  REFERENCES customers (id_customer),
  FOREIGN KEY (id_project)
  REFERENCES projects (id_project),
  CONSTRAINT PK_CUS_PR PRIMARY KEY (id_customer, id_project)
);