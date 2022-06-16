import { renderFile } from "https://deno.land/x/eta@v1.12.3/mod.ts";
import * as itemService from "../services/itemService.js";
import * as listService from "../services/listService.js";
import * as requestUtils from "../utils/requestUtils.js";

const responseDetails = {
  headers: { "Content-Type": "text/html;charset=UTF-8" },
};

const addItem = async (request) => {
  const url = new URL(request.url);
  const urlParts = url.pathname.split("/");
  const listId = urlParts[2];

  const formData = await request.formData();
  const name = formData.get("name");

  await itemService.create(listId, name);

  return requestUtils.redirectTo(`/lists/${listId}`);
};

const viewItems = async (request) => {
  const url = new URL(request.url);
  const urlParts = url.pathname.split("/");
  const listId = urlParts[2];

  const items = await itemService.findAllItemsByListId(listId);

  const data = {
    list: await listService.findListById(listId),
    items: items,
  };

  return new Response(await renderFile("items.eta", data), responseDetails);
};

const collectItem = async (request) => {
  const url = new URL(request.url);
  const urlParts = url.pathname.split("/");

  const listId = urlParts[2];
  const itemId = urlParts[4];

  await itemService.collect(itemId);

  return requestUtils.redirectTo(`/lists/${listId}`);
};

export { addItem, collectItem, viewItems };
