/*Queries and DML*/

/* SELECT*/

-- a. Select all records from the Employee table.

select * 
from "Employee";

--b Select all records from the Employee table where last name is King.
select *
from "Employee"
where "LastName" = 'King';

-- c Select all albums in Album table and sort result set in descending order by title.
select *
from 
     "Album"
order by "Title" desc


--- d Select first name from Customer and sort result set in ascending order by city.
select "FirstName"
from
  "Customer"
order by "City" asc


-- e Select all invoices with a billing address like “T%”.
select *
from
    "Invoice"
where
    "BillingAddress" like 'T%';

-- f Select the name of the longest track.
select "Name"
from 
    "Track"
where "Bytes" = 
    ( 
        select max("Bytes") 
         from "Track"

     );

---g Find the average invoice total.
select  round(avg("Total"), 2)
from
    "Invoice"
      
---h Find the total number of employees in each position.
select  count("EmployeeId"), "Title"
from
    "Employee"
group by "Title";


/*2.2 INSERT INTO*/
--a Insert two new records into Genre table
insert into "Genre" ("GenreId", "Name") values (26, 'Tragedy');
insert into "Genre" ("GenreId", "Name") values (27, 'Soothing');


-- b Insert two new records into Employee table
insert into "Employee" ("EmployeeId","LastName", "FirstName", "Title", "ReportsTo", "BirthDate","HireDate","Address","City", "State","Country", "PostalCode", "Phone", "Fax", "Email") values (9, N'Brighten', N'Cindy', N'IT Staff', 6, '1978/3/14', '2005/7/1', N'1100 Main Ave NW', N'Hernton', N'NJ', N'U.S.A', N'02451', N'+1 (762) 351-9276', N'+1 (762) 428-3457', N'cindy@chinookcorp.com'); 
insert into "Employee" ("EmployeeId", "LastName", "FirstName","Title","ReportsTo", "BirthDate", "HireDate", "Address","City","State","Country","PostalCode","Phone","Fax","Email") values(10, N'Armstrong', N'Bob', N'Sales Surpport Agent',2, '1973/8/9', '2005/3/15', N'18 Fox Run Rd', N'Newton', N'CN', N'Canada', N'D1k 2N1', N'+1 (615) 323-5471', N'+1 (615) 814-9298', N'bob@chinookcorp.com');

---c Insert two new records into Customer table 
insert into "Customer" ("CustomerId", "FirstName", "LastName", "Company", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email", "SupportRepId") values (60, N'Dan', N'Braden', 'Giant',  '76 Marborough RD','Belmont', 'NH', 'USA', '10272','+ 01 219 031 0322','+ 01 219 031 0320', N'dan_brady@apple.be', 4);
insert into "Customer" ("CustomerId", "FirstName", "LastName", "Company", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email", "SupportRepId") values (61, N'Wendy', N'Zhang', 'Beyond', '32 Forest St', 'GrandRapids', 'MI', 'USA', '23145','+01 617 516 8741','+ 01 617 516 8740','wendy_zhang@yahho.com',5);


--Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
update "Artist"
set "Name" = 'CCR'
where 
    "Name" = 'Creedence Clearwater Revival';


/*3.0 Joins
In this section you will be working with combining various tables through the use of joins. You will work with outer, inner, right, left, cross, and self joins.
*/

/*3.1 INNER*/
--- Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
select c."FirstName" || ' ' || c."LastName" as "Name", i."InvoiceId" invoiceId
from 
    "Customer" c
join 
   "Invoice" i
on c."CustomerId" = i."CustomerId";
   
/*3.2 OUTER*/
---Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
select  c."CustomerId" id, c."FirstName" || ' ' || c."LastName" as Name, i."InvoiceId" as invoice_id, i."Total" total
from 
     "Customer" c
full join 
     "Invoice"  i
on 
     c."CustomerId" = i."CustomerId";

/*3.3 RIGHT*/
---Create a right join that joins album and artist specifying artist name and title.
select 
   ar."Name" Artist_Name, al."Title" Album_Title
from 
   "Album" al
RIGHT JOIN
   "Artist" ar
on
   al."ArtistId" = ar."ArtistId";
  
  
   

  /*3.4 CROSS */
---Create a cross join that joins album and artist and sorts by artist name in ascending order.

  select ar."ArtistId" artistId, ar."Name" artistName, al."Title" AlbumTitle
  from "Album" al
  cross join "Artist" ar
--  on al."ArtistId" = ar."ArtistId"
  order by ar."Name" asc;



/*3.5 SELF*/
---Perform a self-join on the employee table, joining on the reportsto column.
select e."FirstName" || ' '||e."LastName" employee, m."FirstName" || ''|| m."LastName" manager
from "Employee" e
inner join "Employee" m
on e."ReportsTo"= m."EmployeeId"
order by manager;
  
/*3.6 Joined Queries */
--a Create a query that shows the customer first name and last name as FULL_NAME (you can use || to concatenate two strings) with the total amount of money they have spent as TOTAL.
select c."FirstName" || ' ' || c."LastName" Customer, sum(i."Total")
from 
    "Customer" c
left join "Invoice" i
on c."CustomerId" = i."CustomerId"
group by c."CustomerId";

--b. Create a query that shows the employee that has made the highest total value of sales (total of all invoices).
create view employee_total_sale as
select e."EmployeeId" employeeid, e."FirstName" ||' '|| e."LastName" Employee, sum(i."Total") Total
from "Employee" e
left join "Customer" c
on e."EmployeeId" = c."SupportRepId"
left join "Invoice" i
on c."CustomerId" = i."CustomerId"
group by e."EmployeeId";

select employeeId, Employee, Total
from employee_total_sale
where Total = (select max(total) from employee_total_sale);


/*select et.employeeId, et.employee, et.total
from 
     (select e."EmployeeId" EmployeeId, e."FirstName" ||' '|| e."LastName" Employee, sum(i."Total") Total
       from "Employee" e
      left join "Customer" c
      on e."EmployeeId" = c."SupportRepId"
      left join "Invoice" i
      on c."CustomerId" = i."CustomerId"
      group by e."EmployeeId") as et
 where et.Total = (select max(et.Total) from et); n*/


-- c. Create a query which shows the number of purchases per each genre. Display the name of each genre and number of purchases. Show the most popular genre first.
select g."GenreId" GenreId, g."Name" GenreName, sum(i."Quantity") purchase
from 
    "Genre" g
join 
     "Track" t
on  g."GenreId" = t."GenreId"
join 
     "InvoiceLine" i
on  t."TrackId" = i."TrackId"
group by g."GenreId"
order by purchase desc;


/*4.0 User Defined Functions*/
--a Create a function that returns the average total of all invoices.
create or replace function AveTotal()
returns numeric
language plpgsql
as $$

   declare average numeric;
begin  
   select round(avg("Total"),2) into average
   from "Invoice";
  
   return average;
end;
$$
   
 select  AveTotal(); 

--b Create a function that returns all employees who are born after 1968.

create or replace function EmplBornAfterCertainYear()
returns setof "Employee"
language plpgsql
as $$
declare 
       year timestamp = '1968-01-01 00:00:00';
begin
	   return query select * from "Employee" where "BirthDate" > year;
end
$$

select EmplBornAfterCertainYear();



--c Create a function that returns the manager of an employee, given the id of the employee.
create or replace function getManager(id int)
returns setof "Employee"
language plpgsql
as $function$
declare 
     manager_id int; 
begin 
	 select "ReportsTo" into manager_id
	 from "Employee" where "EmployeeId" = id;
	 
	 if manager_id isnull then 
	    return null;
	 else
	    return query select * from "Employee" where "EmployeeId" = manager_id;
	 end if;
end;
$function$

select getManager(2);


--d Create a function that returns the price of a particular playlist, given the id for that playlist.
create or replace function priceOfPlaylist(playlistId int)
returns numeric
language plpgsql
as $$
declare
    price numeric;

begin    
	    select sum(t."UnitPrice") into price
	    from "PlaylistTrack" pk
	    join "Track" t
	    on pk."TrackId" = t."TrackId"
	    where pk."PlaylistId" = playlistId;
	   
	    return price;
end
$$

select priceOfPlaylist(1);
