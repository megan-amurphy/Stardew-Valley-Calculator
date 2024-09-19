SELECT 
    c.crop_name, 
    c.crop_season, 
    c.normal_price, 
    c.silver_price, 
    c.gold_price, 
    c.iridium_price, 
    s.seed_name, 
    sl.seed_price_lower, 
    sl.seed_price_upper, 
    sl.item_non_gold, 
    sl.is_out_of_season
FROM 
    crops c
JOIN 
    crop_seed cs ON c.crop_id = cs.crop_id
JOIN 
    seeds s ON cs.seed_id = s.seed_id
JOIN 
    seed_location sl ON s.seed_id = sl.seed_id
JOIN 
    purchase_location pl ON sl.location_id = pl.purchase_location_id
WHERE 
    c.crop_name = 'Blue Jazz' 
    AND s.seed_name = 'Jazz Seeds' 
    AND pl.purchase_location_name = 'JojaMart';