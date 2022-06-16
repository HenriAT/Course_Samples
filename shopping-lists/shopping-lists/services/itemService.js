import { executeQuery } from "../database/database.js";

const create = async (listId, name) => {
  await executeQuery(
    "INSERT INTO shopping_list_items (shopping_list_id, name) VALUES ($1, $2);",
    listId,
    name,
  );
};

const findAllItemsByListId = async (listId) => {
  const result = await executeQuery(
    "SELECT * FROM shopping_list_items WHERE shopping_list_id = $1 ORDER BY collected, name",
    listId,
  );
  return result.rows;
};

const collect = async (itemId) => {
  await executeQuery(
    "UPDATE shopping_list_items SET collected = true WHERE id = $1;",
    itemId,
  );
};

export { collect, create, findAllItemsByListId };
