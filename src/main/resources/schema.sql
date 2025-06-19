CREATE TABLE IF NOT EXISTS Family (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    code TEXT UNIQUE NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS FormatDescription (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    family_id INTEGER NOT NULL,
    length INTEGER NOT NULL,
    code TEXT UNIQUE NOT NULL,
    FOREIGN KEY (family_id) REFERENCES Family(id)
);

CREATE TABLE IF NOT EXISTS Field (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    format_description_id INTEGER NOT NULL,
    field_key TEXT,
    label TEXT,
    absolute_type TEXT,
    in_string_type TEXT,
    description TEXT,
    offset INTEGER,
    length INTEGER,
    FOREIGN KEY (format_description_id) REFERENCES FormatDescription(id)
);

CREATE TABLE IF NOT EXISTS DataValue (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    field_id INTEGER NOT NULL,
    value TEXT NOT NULL,
    source_file TEXT,
    line_number INTEGER,
    FOREIGN KEY (field_id) REFERENCES Field(id)
);