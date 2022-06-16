import { executeQuery } from "../database/database.js";

const listCount = async () => {
  return await executeQuery("SELECT COUNT(id) FROM shopping_lists;");
};

const itemCount = async () => {
  return await executeQuery(
    "SELECT COUNT(id) FROM shopping_list_items;",
  );
};

export { itemCount, listCount };
