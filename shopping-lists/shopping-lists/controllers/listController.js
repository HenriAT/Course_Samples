import { renderFile } from "https://deno.land/x/eta@v1.12.3/mod.ts";
import * as listService from "../services/listService.js";
import * as requestUtils from "../utils/requestUtils.js";

const responseDetails = {
  headers: { "Content-Type": "text/html;charset=UTF-8" },
};

const addList = async (request) => {
  const formData = await request.formData();
  const name = formData.get("name");

  await listService.create(name);

  return requestUtils.redirectTo("/lists");
};

const viewLists = async (_request) => {
  const data = {
    lists: await listService.findAllActiveLists(),
  };

  return new Response(await renderFile("lists.eta", data), responseDetails);
};

const deactivateList = async (request) => {
  const url = new URL(request.url);
  const urlParts = url.pathname.split("/");
  await listService.deactivate(urlParts[2]);

  return requestUtils.redirectTo("/lists");
};

export { addList, deactivateList, viewLists };
