//package com.mk.elasticsearch;
//
//import com.alibaba.fastjson.JSON;
//import org.apache.http.HttpHost;
//import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
//import org.elasticsearch.action.bulk.BulkRequest;
//import org.elasticsearch.action.bulk.BulkResponse;
//import org.elasticsearch.action.delete.DeleteRequest;
//import org.elasticsearch.action.delete.DeleteResponse;
//import org.elasticsearch.action.get.GetRequest;
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.action.support.master.AcknowledgedResponse;
//import org.elasticsearch.action.update.UpdateRequest;
//import org.elasticsearch.action.update.UpdateResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.client.indices.CreateIndexRequest;
//import org.elasticsearch.client.indices.CreateIndexResponse;
//import org.elasticsearch.client.indices.GetIndexRequest;
//import org.elasticsearch.client.indices.GetIndexResponse;
//import org.elasticsearch.common.xcontent.XContentType;
//import org.elasticsearch.index.query.BoolQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.index.query.RangeQueryBuilder;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.SearchHits;
//import org.elasticsearch.search.aggregations.AggregationBuilder;
//import org.elasticsearch.search.aggregations.AggregationBuilders;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.elasticsearch.search.sort.SortOrder;
//import org.junit.Test;
//
//import java.io.IOException;
//
//public class EsTest
//{
//    @Test
//    public void createIndex() throws IOException
//    {
//        RestHighLevelClient client = getClient();
//        //????????????
//        CreateIndexRequest createIndexRequest = new CreateIndexRequest("user");
//        CreateIndexResponse response = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
//        boolean status = response.isAcknowledged();
//        System.out.println(String.format("???????????????????????????%s", status));
//
//        client.close();
//    }
//
//    /**
//     * ????????????
//     */
//    @Test
//    public void searchIndex() throws IOException
//    {
//        RestHighLevelClient client = getClient();
//        //????????????
//        GetIndexRequest getIndexRequest = new GetIndexRequest("user");
//        GetIndexResponse response = client.indices().get(getIndexRequest, RequestOptions.DEFAULT);
//        System.out.println(String.format("???????????????%s", response.getAliases()));
//        System.out.println(String.format("???????????????%s", response.getMappings()));
//        System.out.println(String.format("???????????????%s", response.getSettings()));
//
//        client.close();
//    }
//
//    /**
//     * ????????????
//     */
//    @Test
//    public void deleteIndex() throws IOException
//    {
//        RestHighLevelClient client = getClient();
//        //????????????
//        DeleteIndexRequest request = new DeleteIndexRequest("user");
//        AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
//        System.out.println(String.format("???????????????%s", response.isAcknowledged()));
//
//        client.close();
//    }
//
//    /**
//     * ????????????
//     */
//    @Test
//    public void insertDoc() throws IOException
//    {
//        RestHighLevelClient client = getClient();
//
//        //????????????
//        IndexRequest request = new IndexRequest();
//        request.index("user").id("110");
//        User user = new User();
//        String jsonStr = JSON.toJSONString(user);
//        request.source(jsonStr, XContentType.JSON);
//        //??????
//        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
//
//        System.out.println(String.format("???????????????%s", response.getResult()));
//
//        client.close();
//    }
//
//    /**
//     * ????????????
//     */
//    @Test
//    public void updateDoc() throws IOException
//    {
//        RestHighLevelClient client = getClient();
//
//        //????????????
//        UpdateRequest request = new UpdateRequest();
//        request.index("user").id("110");
//        request.doc(XContentType.JSON, "sex", "???");
//
//        //??????
//        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
//
//        System.out.println(String.format("???????????????%s", response.getResult()));
//
//        client.close();
//    }
//
//    /**
//     * ????????????
//     */
//    @Test
//    public void selectDoc() throws IOException
//    {
//        RestHighLevelClient client = getClient();
//
//        GetRequest request = new GetRequest();
//        request.index("user").id("110");
//        //??????
//        GetResponse response = client.get(request, RequestOptions.DEFAULT);
//
//        System.out.println(String.format("???????????????%s", response.getSourceAsString()));
//
//        client.close();
//    }
//
//    /**
//     * ????????????
//     */
//    @Test
//    public void deleteDoc() throws IOException
//    {
//        RestHighLevelClient client = getClient();
//
//        DeleteRequest request = new DeleteRequest();
//        request.index("user").id("110");
//        //??????
//        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
//
//        System.out.println(String.format("???????????????%s", response.getResult()));
//
//        client.close();
//    }
//
//    /**
//     * ????????????
//     */
//    @Test
//    public void mInsertDoc() throws IOException
//    {
//        RestHighLevelClient client = getClient();
//
//        BulkRequest bulkRequest = new BulkRequest();
//        bulkRequest.add(new IndexRequest().index("user").id("111").source(XContentType.JSON, "name", "lily_v2", "age", "18", "sex", "???"));
//        bulkRequest.add(new IndexRequest().index("user").id("112").source(XContentType.JSON, "name", "danny_v2", "age", "89", "sex", "???"));
//        bulkRequest.add(new IndexRequest().index("user").id("113").source(XContentType.JSON, "name", "liming_v2", "age", "85", "sex", "???"));
//        bulkRequest.add(new IndexRequest().index("user").id("114").source(XContentType.JSON, "name", "zhangsan", "age", "19", "sex", "???"));
//        bulkRequest.add(new IndexRequest().index("user").id("115").source(XContentType.JSON, "name", "lisi", "age", "65", "sex", "???"));
//        bulkRequest.add(new IndexRequest().index("user").id("116").source(XContentType.JSON, "name", "wangwu", "age", "60", "sex", "???"));
//
//        BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
//        System.out.println(String.format("?????????????????????%s", bulkResponse.status()));
//
//        client.close();
//    }
//
//    /**
//     * ????????????
//     */
//    @Test
//    public void mDeleteDoc() throws IOException
//    {
//        RestHighLevelClient client = getClient();
//
//        BulkRequest bulkRequest = new BulkRequest();
//
//        bulkRequest.add(new DeleteRequest().index("user").id("111"));
//        bulkRequest.add(new DeleteRequest().index("user").id("112"));
//        bulkRequest.add(new DeleteRequest().index("user").id("113"));
//
//        BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
//        System.out.println(String.format("?????????????????????%s", bulkResponse.status()));
//
//        client.close();
//    }
//
//    /**
//     * ????????????
//     */
//    @Test
//    public void mUpdateDoc() throws IOException
//    {
//        RestHighLevelClient client = getClient();
//
//        BulkRequest bulkRequest = new BulkRequest();
//
//        bulkRequest.add(new UpdateRequest().index("user").id("111").doc(XContentType.JSON, "name", "lily_v2", "age", "18", "sex", "???"));
//        bulkRequest.add(new UpdateRequest().index("user").id("112").doc(XContentType.JSON, "name", "danny_v2", "age", "19", "sex", "???"));
//        bulkRequest.add(new UpdateRequest().index("user").id("113").doc(XContentType.JSON, "name", "liming_v2", "age", "20", "sex", "???"));
//
//        BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
//        System.out.println(String.format("?????????????????????%s", bulkResponse.status()));
//
//        client.close();
//    }
//
//    /**
//     * ??????????????????
//     */
//    @Test
//    public void mSelectDocAll() throws IOException
//    {
//        RestHighLevelClient client = getClient();
//
//        SearchRequest request = new SearchRequest();
//        //????????????
//        request.indices("user");
//        //??????????????????
//        request.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));
//        //??????
//        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//
//        SearchHits hits = response.getHits();
//
//        System.out.println(String.format("???????????????%s", response.getTook()));
//        for (SearchHit hit : hits)
//        {
//            System.out.println(String.format("????????????%s", hit.getSourceAsString()));
//        }
//
//        client.close();
//    }
//
//    /**
//     * ????????????
//     */
//    @Test
//    public void pageSelectDoc() throws IOException
//    {
//        RestHighLevelClient client = getClient();
//
//        SearchRequest request = new SearchRequest();
//        //????????????
//        request.indices("user");
//        //??????????????????
//        SearchSourceBuilder searchBuilder = new SearchSourceBuilder().
//                query(QueryBuilders.matchQuery("sex", "???")).
//                query(QueryBuilders.existsQuery("sex"));
//
//        searchBuilder.from(0).size(4); //??????
//        searchBuilder.sort("age", SortOrder.ASC);//??????
//        //??????????????????
//        String[] includeFields = {"name", "age"};
//        //???????????????
//        String[] excludeFields = {};
//        searchBuilder.fetchSource(includeFields, excludeFields);
//
//        //??????????????????
//        request.source(searchBuilder);
//
//        //??????
//        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//
//        SearchHits hits = response.getHits();
//
//        System.out.println(String.format("???????????????%s", response.getTook()));
//        for (SearchHit hit : hits)
//        {
//            System.out.println(String.format("????????????%s", hit.getSourceAsString()));
//        }
//
//        client.close();
//    }
//
//    /**
//     * ??????????????????????????????
//     */
//    @Test
//    public void scopeSelectDoc() throws IOException
//    {
//        RestHighLevelClient client = getClient();
//
//        SearchRequest request = new SearchRequest();
//        //????????????
//        request.indices("user");
//        //??????????????????
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        SearchSourceBuilder searchBuilder = new SearchSourceBuilder().query(boolQueryBuilder);
//        boolQueryBuilder.must(QueryBuilders.matchQuery("sex", "???")); //?????????
//        boolQueryBuilder.mustNot(QueryBuilders.matchQuery("age", "19"));//????????????
//        boolQueryBuilder.should(QueryBuilders.matchQuery("sex", "???"));//?????????
//
//        //??????????????????
//        request.source(searchBuilder);
//
//        //??????
//        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//
//        SearchHits hits = response.getHits();
//
//        System.out.println(String.format("???????????????%s", response.getTook()));
//        for (SearchHit hit : hits)
//        {
//            System.out.println(String.format("????????????%s", hit.getSourceAsString()));
//        }
//
//        client.close();
//    }
//
//    /**
//     * ??????????????????????????????
//     */
//    @Test
//    public void scopeSelectDoc2() throws IOException
//    {
//        RestHighLevelClient client = getClient();
//
//        SearchRequest request = new SearchRequest();
//        //????????????
//        request.indices("user");
//        //????????????
//        //        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("age");
//        //        SearchSourceBuilder searchBuilder = new SearchSourceBuilder().query(rangeQueryBuilder);
//        ////        rangeQueryBuilder.from("18").to("100");//????????????
//        //        rangeQueryBuilder.lt("20").gt("15");//????????????
//        //        request.source(searchBuilder);
//
//        //        ????????????
//        AggregationBuilder aggregationBuilder = AggregationBuilders.terms("ageGroup").field("age");
//        SearchSourceBuilder searchBuilder = new SearchSourceBuilder().aggregation(aggregationBuilder);
//        request.source(searchBuilder);
//
//        //??????
//        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//        SearchHits hits = response.getHits();
//
//        System.out.println(String.format("???????????????%s", response.getAggregations().toString()));
//
//        client.close();
//    }
//
//    /**
//     * ??????es?????????
//     *
//     * @return
//     */
//    private RestHighLevelClient getClient()
//    {
//        return new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.62.130", 9200, "http")));
//    }
//}
