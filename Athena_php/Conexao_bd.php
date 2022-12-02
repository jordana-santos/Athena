<?php

// Abre uma conexao com o BD.

$host        = "host = kesavan.db.elephantsql.com;";
$port        = "port = 5432;";
$dbname      = "dbname = ewhioity;";
$dbuser 	 = "ewhioity";
$dbpassword	 = "X17lhDQUwBQkn2M6lup2sWuHfj6AQnOm";

// dados de conexao com o railway. Usar somente caso esteja usando railway
//$host        = "host = " . getenv("PGHOST") . ";";
//$port        = "port = " . getenv("PGPORT") . ";";
//$dbname      = "dbname = " . getenv("PGDATABASE") . ";";
//$dbuser 	 = getenv("PGUSER");
//$dbpassword	 = getenv("PGPASSWORD");

// para conectar ao mysql, substitua pgsql por mysql
$db_con= new PDO('pgsql:' . $host . $port . $dbname, $dbuser, $dbpassword);

//alguns atributos de performance.
$db_con->setAttribute(PDO::ATTR_EMULATE_PREPARES,false);
$db_con->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);
?>