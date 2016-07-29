create view user_posting as
select
	postings.id as posting_id,
	postings.user_id as user_id,
	branch_id,
	position_id,
	name,
	title,
	postings.text as text,
	category,
	postings.insert_date as insert_date
from
	users, postings,
where
	users.id = postings.user_id;