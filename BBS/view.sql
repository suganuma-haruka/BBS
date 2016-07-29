CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `bbs`.`user_posting` AS
    select 
        `bbs`.`postings`.`id` AS `posting_id`,
        `bbs`.`postings`.`user_id` AS `user_id`,
        `bbs`.`users`.`branch_id` AS `branch_id`,
        `bbs`.`users`.`position_id` AS `position_id`,
        `bbs`.`users`.`name` AS `name`,
        `bbs`.`postings`.`title` AS `title`,
        `bbs`.`postings`.`text` AS `text`,
        `bbs`.`postings`.`category` AS `category`,
        `bbs`.`postings`.`insert_date` AS `insert_date`
    from
        (`bbs`.`users`
        join `bbs`.`postings`)
    where
        (`bbs`.`users`.`id` = `bbs`.`postings`.`user_id`)
        

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `bbs`.`user_comment` AS
    select 
        `bbs`.`comments`.`id` AS `comment_id`,
        `bbs`.`comments`.`posting_id` AS `posting_id`,
        `bbs`.`comments`.`user_id` AS `user_id`,
        `bbs`.`users`.`branch_id` AS `branch_id`,
        `bbs`.`users`.`position_id` AS `position_id`,
        `bbs`.`users`.`name` AS `name`,
        `bbs`.`comments`.`text` AS `text`,
        `bbs`.`comments`.`insert_date` AS `insert_date`
    from
        (`bbs`.`users`
        join `bbs`.`comments`)
    where
        (`bbs`.`users`.`id` = `bbs`.`comments`.`user_id`)